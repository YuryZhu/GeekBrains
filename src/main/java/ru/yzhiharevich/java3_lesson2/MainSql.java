package ru.yzhiharevich.java3_lesson2;

import java.sql.*;
import java.util.Scanner;

public class MainSql {
    private static Connection connection;
    private static Statement stmt;

    public static void main(String[] args) throws SQLException {
        try {
            Class.forName("org.sqlite.JDBC");
            // строка подключения
            connection = DriverManager.getConnection("jdbc:sqlite:mainDB25102018.db");
            // установка подключения
            stmt = connection.createStatement();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        stmt.executeUpdate("DELETE from products");
        connection.setAutoCommit(false);
        for (int i = 1; i <= 100; i++) {
            String sql = String.format("INSERT into products (prodid,title,cost)VALUES('%s','%s','%s')", "id_товара " + i, "товар" + i, i * 10);
            stmt.executeUpdate(sql);
        }
        connection.setAutoCommit(true);
        while (true) {
            ResultSet rs;
            Scanner in = new Scanner(System.in);
            System.out.println("Напишите help, чтобы получить список доступных команд");
            String str = in.nextLine();
            String[] tokens = str.split(" ");
            if (tokens[0].equals("help")) {
                System.out.println("цена товар+ID, показывает цену товара");
                System.out.println("сменитьцену товар+ID новая_цена, меняет цену товара");
                System.out.println("товарыпоцене 100 600, показывает товары в заданном ценовом диапазоне");
                System.out.println("выход, выход из консоли");
            } else if (tokens[0].equals("цена")) {
                String sql = String.format("SELECT title, cost from products where title = '%s'", tokens[1]);
                rs = stmt.executeQuery(sql);
                while (rs.next()) {
                    System.out.println(rs.getString(1) + " | " + rs.getString(2));
                }
            } else if (tokens[0].equals("сменитьцену")) {
                String sql = String.format("update products set cost='%s' where title = '%s'", tokens[2], tokens[1]);
                String sql2 = String.format("SELECT title, cost from products where title = '%s'", tokens[1]);
                stmt.executeUpdate(sql);
                rs = stmt.executeQuery(sql2);
                while (rs.next()) {
                    System.out.println("Новая цена товара = " + rs.getString(1) + " | " + rs.getString(2));
                }
            } else if (tokens[0].equals("товарыпоцене")) {
                String sql = String.format("SELECT title, cost from products where cost between '%s' and  '%s'", tokens[1], tokens[2]);
                rs = stmt.executeQuery(sql);
                while (rs.next()) {
                    System.out.println(rs.getString(1) + " | " + rs.getString(2));
                }
            } if (tokens[0].equals("выход")) break;
        }
    }
}
