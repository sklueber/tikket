/*
 * Informatikprojekt aus 2019. Erstellt von Simon und Max.
 * Zuletzt bearbeitet 02.04.19 01:36 .
 * Keiner klaut das hier! Copyright tikket (c) 2019.
 */

package com.tikket.tikketServer;

import java.io.*;
import java.net.ServerSocket;
import java.net.Socket;
import java.sql.*;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.concurrent.ThreadLocalRandom;

public class tikketServer {
    private int SrvVa_ID;
    private String SrvVa_name;
    private final ServerSocket server;

    public tikketServer(int port) throws IOException {
        ServerSocket listener = null;

        //TEST
        SrvVa_ID = 1;
        SrvVa_name = "testVA";

        System.out.println("Server wartet auf Nutzer...");
        int clientNumber = 0;

        try {
            listener = new ServerSocket(port);
        } catch (IOException e) {
            System.out.println(e);
            System.exit(1);
        }

        try {
            while (true) {
                Socket socketOfServer = listener.accept();
                new ServiceThread(socketOfServer, clientNumber++).start();
            }
        } finally {
            listener.close();
        }
    }

    private void log(String message) {
        System.out.println(message);
    }

    private class ServiceThread extends Thread {

        private int clientNumber;
        private Socket socketOfServer;

        public ServiceThread(Socket socketOfServer, int clientNumber) {
            this.clientNumber = clientNumber;
            this.socketOfServer = socketOfServer;

            // Log
            log("Neuer tikketClient registriert. tikketClient# " + this.clientNumber + " auf " + socketOfServer);
        }

        @Override
        public void run() {
            try {
                BufferedReader is = new BufferedReader(new InputStreamReader(socketOfServer.getInputStream()));
                BufferedWriter os = new BufferedWriter(new OutputStreamWriter(socketOfServer.getOutputStream()));

                while (true) {
                    /*Hier wird auf die Befehle aus dem Protokoll reagiert*/
                    String line = is.readLine();
                    if (line.equals("serverTest")) {
                        os.write("-->>OK");
                        os.newLine();
                        os.flush();
                    }
                    if (line.equals("ticketErstellen")) {
                        ticketErstellen();
                        os.write("-->>OK");
                        os.newLine();
                        os.flush();
                    }
                    if (line.equals("ticketAusgeben")) {
                        os.write(ticketAusgeben());
                        os.newLine();
                        os.flush();
                    }
                    if (line.contains("ticketPruefen")) {
                        String[] split = line.split(":");
                        int uuid = Integer.parseInt(split[1]);
                        if (ticketPruefen(uuid)) {
                            os.write("-->>TRUE");
                            os.newLine();
                            os.flush();
                        } else {
                            os.write("-->>FALSE");
                            os.newLine();
                            os.flush();
                        }

                    }
                    if (line.contains("ticketAuslass")) { // TODO: 28.03.2019 schreiben
                        String[] split = line.split(":");
                        int uuid = Integer.parseInt(split[1]);
                        TicketEinlass(uuid);
                        os.write("-->>OK");
                        os.newLine();
                        os.flush();
                    }
                    if (line.contains("ticketEinlass")) { // TODO: 28.03.2019 testen
                        String[] split = line.split(":");
                        int uuid = Integer.parseInt(split[1]);
                        TicketEinlass(uuid);
                        os.write("-->>OK");
                        os.newLine();
                        os.flush();
                    }
                    if (line.equals("aktuelleVeranstaltungAuslesen")) {
                        int id = SrvVa_ID;
                        String name = SrvVa_name;
                        os.write(id + "*" + name);
                        os.newLine();
                        os.flush();
                    }
                    if (line.equals("veranstaltungSetzen")) { // TODO: 28.03.2019 NOK Fall hinzufügen

                        veranstaltungWechseln(1);
                        os.write("-->>OK");
                        os.newLine();
                        os.flush();
                    }
                    if (line.equals("veranstaltungAusgeben")) {
                        os.write(veranstaltungAusgeben());
                        os.newLine();
                        os.flush();
                    }
                    if (line.contains("veranstalterErstellen")) { // TODO: 28.03.2019 schreiben
                    }
                    if (line.equals("veranstalterAusgeben")) { // TODO: 28.03.2019 schreiben
                    }
                    if (line.contains("veranstaltungLoeschen")) {// TODO: 28.03.2019 schreiben
                    }
                    if (line.equals("serverTest")) {
                        os.write("-->>OK");
                        os.newLine();
                        os.flush();
                    }
                    if (line.equals("-->>QUIT")) {
                        os.write("-->>OK");
                        os.newLine();
                        os.flush();
                        break;
                    }
                }
            } catch (IOException e) {
                System.out.println(e);
                e.printStackTrace();
            }
        }
    }

    public static Connection DBconnect() {
        Connection conn = null;
        try {
            String url = "jdbc:sqlite:" + tikketServer.class.getClassLoader().getResource("database/tikket_db.db").toString(); //Location der Datenbank
            conn = DriverManager.getConnection(url);
            System.out.println("Verbindung zur tikket-Datenbank hergestellt");
            return conn;
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
        return conn;
    }

    private void ticketErstellen() {
        Date date = new Date();
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss.S");
        String currentDate = dateFormat.format(date);

        int zufall = ThreadLocalRandom.current().nextInt(0, 1000000000);

        try (Connection conn = DBconnect()) {
            String sqlInsertTicket = "INSERT INTO tickets(tkt_UUID, tkt_status, tkt_created, tkt_va) VALUES(?, ?,?,?)";
            try (PreparedStatement pstmt = conn.prepareStatement(sqlInsertTicket)) {
                pstmt.setInt(1, zufall); //TODO in Java schon Unique machen. Ist bisher "nur" random
                pstmt.setInt(2, 1);
                pstmt.setString(3, currentDate);
                pstmt.setInt(4, SrvVa_ID);
                pstmt.executeUpdate();
            }
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
    }

    private boolean ticketPruefen(int UUID) {
        String sql = "SELECT tkt_ID, tkt_UUID, tkt_status FROM  tickets WHERE tkt_UUID = " + UUID + " AND tkt_va = " + SrvVa_ID;

        try (Connection conn = DBconnect()) {
            try (Statement stmt = conn.createStatement()) {
                try (ResultSet rs = stmt.executeQuery(sql)) {
                    if (rs.getInt("tkt_status") == 1) {
                        return true;
                    }
                    if (rs.isClosed()) {
                        return false;
                    }
                }
            }
        } catch (SQLException e) {
            if (e.getMessage().equals("ResultSet closed")) {
                System.err.println(e.getMessage());
            }
            return false;
        }
        return false;
    }

    public String ticketAusgeben() {
        String sql = "SELECT tkt_ID, tkt_UUID, tkt_status FROM  tickets WHERE tkt_va = " + SrvVa_ID;

        try (Connection conn = DBconnect()) {
            try (Statement stmt = conn.createStatement()) {
                try (ResultSet rs = stmt.executeQuery(sql)) {
                    String rslt = "";
                    while (rs.next()) {
                        rslt =
                                rs.getInt("tkt_ID") + "*" +
                                        rs.getInt("tkt_UUID") + "*" +
                                        rs.getInt("tkt_status") + "//" +
                                        rslt;
                    }
                    return rslt + "-->>OK";
                }
            }
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
        return null;
    }

    public void TicketEinlass(int tUUID) {
        String sql = "UPDATE tickets SET tkt_status = 2 WHERE tkt_UUID = " + tUUID + " AND tkt_va = " + SrvVa_ID;

        try (Connection conn = DBconnect()) {
            try (Statement stmt = conn.createStatement()) {
                ResultSet rs = stmt.executeQuery(sql);
                if (rs.isAfterLast()) {
                    System.out.println("Ticket serverseitig eingelassen");
                }
            }
        } catch (SQLException e) {
            if (e.getMessage().equals("ResultSet closed")) {
                System.out.println(e.getMessage());
            }
            return;
        }
    }

    public void veranstaltungErstellen(String va_name, String va_datum, String va_ort, int va_vr) {
        String sql = "INSERT INTO veranstaltungen(va_name, va_datum, va_ort, va_vr) VALUES(?,?,?,?)";

        try (Connection conn = DBconnect()) {
            try (PreparedStatement pstmt = conn.prepareStatement(sql)) {
                pstmt.setString(1, va_name);
                pstmt.setString(2, va_datum);
                pstmt.setString(3, va_ort);
                pstmt.setInt(4, va_vr);
                pstmt.executeUpdate();
            }
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
    }

    public String veranstaltungAusgeben() {
        String sql = "SELECT va_ID, va_name, va_datum, va_ort FROM  veranstaltungen";

        try (Connection conn = DBconnect()) {
            try (Statement stmt = conn.createStatement()) {
                try (ResultSet rs = stmt.executeQuery(sql)) {
                    String rslt = "";
                    while (rs.next()) {
                        rslt =
                                rs.getInt("va_ID") + "*" +
                                        rs.getString("va_name") + "*" +
                                        rs.getString("va_datum") + "*" +
                                        rs.getString("va_ort") + "//" +
                                        rslt;
                    }
                    return rslt + "-->>OK";
                }
            }
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
        return null;
    }

    void veranstalterErstellen(String vr_name) {
        String sql = "INSERT INTO veranstalter(vr_name) VALUES(?)";

        try (Connection conn = DBconnect()) {
            try (PreparedStatement pstmt = conn.prepareStatement(sql)) {
                pstmt.setString(1, vr_name);
                pstmt.executeUpdate();
            }
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
    }

    private void veranstalterAusgeben() {
        String sql = "SELECT vr_ID, vr_name FROM  veranstalter";

        try (Connection conn = DBconnect()) {
            try (Statement stmt = conn.createStatement()) {
                try (ResultSet rs = stmt.executeQuery(sql)) {
                    //ResultSet durchloopen
                    while (rs.next()) {
                        System.out.println(rs.getInt("vr_ID") + "\t" + rs.getString("vr_name") + "\t");
                    }
                }
            }
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
    }

    private void veranstaltungWechseln(int va_id) {
        String sql = "SELECT va_ID, va_name FROM  veranstaltungen WHERE va_ID = " + va_id;

        try (Connection conn = DBconnect()) {
            try (Statement stmt = conn.createStatement()) {
                try (ResultSet rs = stmt.executeQuery(sql)) {
                    SrvVa_name = rs.getString("va_name");
                    SrvVa_ID = va_id;
                } catch (SQLException e) {
                    System.out.println("Keine Veranstaltung mit der ID gefunden. Error: " + e.getMessage());
                }
            }
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
    }
}