package model.dao.impl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import db.DB;
import db.DbException;
import model.dao.DepartmentDao;
import model.entities.Department;

public class DepartmentDaoJDBC implements DepartmentDao {

	private Connection conn;
	public DepartmentDaoJDBC(Connection conn) {
		this.conn = conn;
	}

	@Override
	public void insert(Department obj) {
	PreparedStatement ps= null;
	ResultSet rs= null;
	
	try {
		ps = conn.prepareStatement(
				
				"INSERT INTO department"+
				"(Name)"+ 
				"VALUES"+ 
				"(?)",
				Statement.RETURN_GENERATED_KEYS
				
				);
		
		ps.setString(1, obj.getName());
		int rowsAffected = ps.executeUpdate();
		
		if(rowsAffected>0) {
			rs = ps.getGeneratedKeys();
			if(rs.next()) {
				
			int id = rs.getInt(1);
			obj.setId(id);
			}
			DB.closeResultSet(rs);
		}
		else {
			throw new DbException("unknow error, no rows affected");
		}
		
		
		
	}catch(SQLException e) {
		throw new DbException(e.getMessage());
	}
	finally {
		DB.closeStatement(ps);
	}
		
	}

	@Override
	public void update(Department obj) {
		PreparedStatement ps = null;
		try {
			ps = conn.prepareStatement(
					
					"UPDATE department "+
					"set name=? "+
					"where id=? "
					);
			ps.setString(1, obj.getName());
			ps.setInt(2, obj.getId());
			
			ps.executeUpdate();
					
		}catch(SQLException e) {
			throw new DbException(e.getMessage());
		}
		finally {
			DB.closeStatement(ps);
		}
		
	}

	@Override
	public void deleteById(int id) {
		PreparedStatement ps = null;
		try {
			ps = conn.prepareStatement(
					"DELETE FROM department "+
					"where department.Id =?" 
					);
			ps.setInt(1, id);
			ps.executeUpdate();
		}
		catch(SQLException e) {
			throw new DbException(e.getMessage());
		}
		finally {
			DB.closeStatement(ps);
		}
		
		
	}

	@Override
	public Department findById(int id) {
		PreparedStatement ps = null;
		ResultSet rs= null;
		
		try {
			ps = conn.prepareStatement(
					
					"SELECT department.* "+
					"FROM department "+
					"WHERE department.Id =?"
					);
			ps.setInt(1, id);
			rs =ps.executeQuery();
			
			if(rs.next()) {
				Department  dep = instantiateDepartment(rs);
				
				return dep;
			}
			return null;
		}
		catch(SQLException e) {
			throw new DbException(e.getMessage());
		}
		finally {
			DB.closeResultSet(rs);
			DB.closeResultSet(rs);
		}
		
	
	}

	@Override
	public List<Department> findAll() {
		PreparedStatement ps =null;
		ResultSet rs= null;
		
		List<Department> deps = new ArrayList<>();
		try {
			ps = conn.prepareStatement(
					
					"SELECT department.* "+
					"FROM department "
					);
			rs = ps.executeQuery();
			while(rs.next()) {
				Department dep = instantiateDepartment(rs);
				deps.add(dep);
				
				
			}
			return deps;
		}
		catch(SQLException e ) {
			throw new DbException(e.getMessage());
		}
		finally {
			DB.closeStatement(ps);
			DB.closeResultSet(rs);
		}
		
	}

	private Department instantiateDepartment(ResultSet rs) throws SQLException {
		Department dep = new Department();
		dep.setId(rs.getInt("Id"));
		dep.setName(rs.getString("Name"));
		return dep;
	}
}
