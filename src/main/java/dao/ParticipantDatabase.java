package dao;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

import model.Participant;

public class ParticipantDatabase {
	public  ArrayList<Participant> getParticipants(){
		Connection con = MyConnection.getConnection();
		ArrayList<Participant> participants = new ArrayList<>();
		try {
			
			Statement statement = con.createStatement();
			ResultSet rs = statement.executeQuery("select * from participants");			
			while(rs.next())
			{
				Participant participant = new Participant();
				participant.setPid(rs.getInt(1));
				participant.setName(rs.getString(2));
				participant.setPhone(rs.getString(3));
				participant.setEmail(rs.getString(4));	
				participant.setBid(rs.getInt(5));	
				participants.add(participant);
			}
			
		}
		catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return participants;
		}
		return participants;
	}
}
