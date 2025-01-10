package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

import model.Participant;

public class ParticipantDatabase {
	public ArrayList<Participant> getParticipants() {
		Connection con = MyConnection.getConnection();
		ArrayList<Participant> participants = new ArrayList<>();
		try {

			Statement statement = con.createStatement();
			ResultSet rs = statement.executeQuery("select * from participants");
			while (rs.next()) {
				Participant participant = new Participant();
				participant.setPid(rs.getInt(1));
				participant.setName(rs.getString(2));
				participant.setPhone(rs.getString(3));
				participant.setEmail(rs.getString(4));
				participant.setBid(rs.getInt(5));
				participants.add(participant);
			}

		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return participants;
		}
		return participants;
	}

	public Participant getParticipant(int id) {
		Connection con = MyConnection.getConnection();
		Participant participant = new Participant();
		String sql = "select * from participants where pid=?";
		PreparedStatement statement = null;

		try {
			statement = con.prepareStatement(sql);
			statement.setInt(1, id);
			ResultSet rs = statement.executeQuery();
			if (rs.next()) {
				participant.setPid(rs.getInt(1));
				participant.setName(rs.getString(2));
				participant.setPhone(rs.getString(3));
				participant.setEmail(rs.getString(4));
				participant.setBid(rs.getInt(5));
			} else {
				System.out.println("Participant not found");
			}

		} catch (SQLException e) {
			e.printStackTrace();
			return participant;
		}

		return participant;
	}

	public boolean addParticipant(Participant newParticipant) {
		Connection con = MyConnection.getConnection();
		String sql = "insert into participants(name, phone, email, bid) values(?, ?, ?, ?)";
		PreparedStatement statement = null;

		try {
			statement = con.prepareStatement(sql);
			statement.setString(1, newParticipant.getName());
			statement.setString(2, newParticipant.getPhone());
			statement.setString(3, newParticipant.getEmail());
			statement.setInt(4, newParticipant.getBid());
			statement.execute();
			statement.close();

		} catch (SQLException e) {
			e.printStackTrace();
			return false;
		}
		return true;
	}

	public boolean updateParticipant(Participant participant) {
		Connection con = MyConnection.getConnection();
		
		System.out.println("update");
		System.out.println(participant);
		try {
			PreparedStatement statement = con
					.prepareStatement("update participants set name=?, phone=?, email=?, bid=? where pid=?");
			statement.setString(1, participant.getName());
			statement.setString(2, participant.getPhone());
			statement.setString(3, participant.getEmail());
			statement.setInt(4, participant.getBid());
			statement.setInt(5, participant.getPid());
			statement.execute();

			statement.close();
		} catch (SQLException e) {
			e.printStackTrace();
			return false;
		}
		return true;
	}

	public boolean deleteParticipant(int pid) {
		Connection con = MyConnection.getConnection();
		try {
			Statement statement = con.createStatement();
			statement.executeUpdate("delete from participants where pid= '"+pid+"'");
		} catch (SQLException e) {
			e.printStackTrace();
			return false;
		}
		return true;
	}
}
