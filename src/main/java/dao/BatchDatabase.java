package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

import model.Batch;

public class BatchDatabase {
	public ArrayList<Batch> getBatches() {
		Connection con = MyConnection.getConnection();
		ArrayList<Batch> batches = new ArrayList<>();
		try {

			Statement statement = con.createStatement();
			ResultSet rs = statement.executeQuery("select * from batch");
			while (rs.next()) {
				Batch batch = new Batch();
				batch.setBid(rs.getInt(1));
				batch.setStarttime(rs.getDate(2));
				batch.setEndtime(rs.getDate(3));
				batch.setTrainerName(rs.getString(4));
				batches.add(batch);
			}

		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return batches;
		}
		return batches;
	}

	public Batch getBatch(int id) {
		Connection con = MyConnection.getConnection();
		Batch batch = new Batch();
		String sql = "select * from batch where bid=?";
		PreparedStatement statement = null;

		try {
			statement = con.prepareStatement(sql);
			statement.setInt(1, id);
			ResultSet rs = statement.executeQuery();
			if (rs.next()) {
				batch.setBid(rs.getInt(1));
				batch.setStarttime(rs.getDate(2));
				batch.setEndtime(rs.getDate(3));
				batch.setTrainerName(rs.getString(4));
			} else {
				System.out.println("Batch not found");
			}

		} catch (SQLException e) {
			e.printStackTrace();
			return batch;
		}

		return batch;
	}

	public boolean addBatch(Batch newBatch) {
		Connection con = MyConnection.getConnection();
		String sql = "insert into batch(starttime, endtime, trainerName) values(?, ?, ?)";
		PreparedStatement statement = null;

		try {
			statement = con.prepareStatement(sql);
			statement.setDate(1, new java.sql.Date(newBatch.getStarttime().getTime()));
			statement.setDate(2, new java.sql.Date(newBatch.getEndtime().getTime()));
			statement.setString(3, newBatch.getTrainerName());
			statement.execute();
			statement.close();

		} catch (SQLException e) {
			e.printStackTrace();
			return false;
		}
		return true;
	}

	public boolean updateBatch(Batch batch) {
		Connection con = MyConnection.getConnection();

		System.out.println("update");
		System.out.println(batch);
		try {
			PreparedStatement statement = con
					.prepareStatement("update batch set starttime=?, endtime=?, trainerName=? where bid=?");
			statement.setDate(1, new java.sql.Date(batch.getStarttime().getTime()));
			statement.setDate(2, new java.sql.Date(batch.getEndtime().getTime()));
			statement.setString(3, batch.getTrainerName());
			statement.setInt(4, batch.getBid());
			statement.execute();

			statement.close();
		} catch (SQLException e) {
			e.printStackTrace();
			return false;
		}
		return true;
	}

	public boolean deleteBatch(int bid) {
		Connection con = MyConnection.getConnection();
		try {
			Statement statement = con.createStatement();
			statement.executeUpdate("delete from batch where bid="+bid);
			statement.executeUpdate("delete from participants where bid="+bid);
		} catch (SQLException e) {
			e.printStackTrace();
			return false;
		}
		return true;
	}
}
