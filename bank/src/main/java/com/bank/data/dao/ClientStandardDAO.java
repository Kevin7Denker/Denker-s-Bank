package com.bank.data.dao;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.ArrayList;

import com.bank.data.jdbc.JDBCBank;
import com.bank.models.ClientStandard;

public class ClientStandardDAO {

    private final JDBCBank jdbcBank = new JDBCBank();
    private final Connection connection = jdbcBank.getConnection();

    public boolean createClientStandard(ClientStandard clientStandard) {

        try {

            var sql = "INSERT INTO ClientStandard_bank (nome, cpf, email, password, telefone, endereco, saldo) VALUES (?, ?, ?, ?, ?, ?, ?)";

            var stmt = connection.prepareStatement(sql);

            stmt.setString(1, clientStandard.getNome());
            stmt.setString(2, clientStandard.getCpf());
            stmt.setString(3, clientStandard.getEmail());
            stmt.setString(4, clientStandard.getPassword());
            stmt.setString(5, clientStandard.getTelefone());
            stmt.setString(6, clientStandard.getEndereco());
            stmt.setDouble(7, clientStandard.getSaldo());

            stmt.execute();

            return true;

        } catch (SQLException e) {
            System.err.println(e.getMessage());
            return false;
        }

    }

    public ClientStandard getClientByEmail(String email) {

        try {

            var sql = "SELECT * FROM ClientStandard_bank WHERE email = ?";

            var stmt = connection.prepareStatement(sql);

            stmt.setString(1, email);

            var rs = stmt.executeQuery();

            if (rs.next()) {

                ClientStandard client = new ClientStandard(rs.getString("nome"), rs.getString("cpf"), rs.getString("email"),
                        rs.getString("password"), rs.getString("telefone"), rs.getString("endereco"), rs.getDouble("saldo"));

                return client;
            }

            return null;

        } catch (SQLException e) {
            System.err.println(e.getMessage());
            return null;
        }

    }

    public ArrayList<ClientStandard> getClientes() {

        try {

            var sql = "SELECT * FROM ClientStandard_bank";

            var stmt = connection.prepareStatement(sql);

            var rs = stmt.executeQuery();

            ArrayList<ClientStandard> clientes = new ArrayList<>();

            while (rs.next()) {

                ClientStandard client = new ClientStandard(rs.getString("nome"), rs.getString("cpf"),
                        rs.getString("email"), rs.getString("password"), rs.getString("telefone"), rs.getString("endereco"),
                        rs.getDouble("saldo"));

                clientes.add(client);
            }

            return clientes;

        } catch (SQLException e) {
            System.err.println(e.getMessage());
            return null;
        }

    }

}
