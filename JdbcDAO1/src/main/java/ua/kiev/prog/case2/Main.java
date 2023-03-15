package ua.kiev.prog.case2;

import ua.kiev.prog.shared.Client;
import ua.kiev.prog.shared.ConnectionFactory;

import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.List;

public class Main {
    public static void main(String[] args) throws SQLException {
        try (Connection conn = ConnectionFactory.getConnection())
        {

            ClientDAOImpl2 dao = new ClientDAOImpl2(conn, "Test");
            dao.createTable(Client.class);

            Client c = new Client("test", 1);
            dao.add(c);
            int id = c.getId(); // DZ1

            List<Client> list = dao.getAll(Client.class);
            for (Client cli : list)
                System.out.println(cli);

            list.get(0).setAge(55);
            dao.update(list.get(0));

            //DZ2

            List<Client> list1 = dao.getAll(Client.class, "name", "age");
            List<Client> list2= dao.getAll(Client.class, "age");
            for (Client cli : list1)
                System.out.println(cli);

            for (Client cli : list2)
                System.out.println(cli);

            dao.delete(list.get(0));
        }
    }
}
