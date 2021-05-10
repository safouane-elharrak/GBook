package ma.fstt.dao;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import ma.fstt.model.Has;

public abstract class BaseHas extends BaseDao<Has> {

	public BaseHas() throws SQLException {
		super();
		// TODO Auto-generated constructor stub
	}

	@Override
	public void save(Has object) throws SQLException {
		
		String sql = "insert into has (date_has, id_book,id_bookstore) values (? ,?,?)";
		
		this.preparedStatement = this.connection.prepareStatement(sql);
		
	// mapping objet relation

		this.preparedStatement.setString(1, object.getDate());
		this.preparedStatement.setObject(2, object.getBook());
		this.preparedStatement.setObject(3, object.getBookStore());
	
		
		this.preparedStatement.execute();
	}

	@Override
	public void update(Has object) throws SQLException {
String sql = "UPDATE `has` SET `date_has`=?,`id_book`=?,`id_bookstore`=? WHERE id_has = ?";
		
		this.preparedStatement = this.connection.prepareStatement(sql);
		
	
		
		this.preparedStatement.setString(1, object.getDate());
		this.preparedStatement.setObject(2, object.getBook());
		this.preparedStatement.setObject(3, object.getBookStore());	
		this.preparedStatement.setInt(4, object.getId_has());
		this.preparedStatement.execute();
		
	}

	@Override
	public void delete(Has object) throws SQLException {
String sql = "delete `has` from `has` WHERE id_has = ?";
		
		this.preparedStatement = this.connection.prepareStatement(sql);
	
		this.preparedStatement.setInt(1, object.getId_has());
		this.preparedStatement.execute();
		
	}

	@Override
	public Has getOne(int id)throws SQLException {
		
		Has cli = new Has();
		String sql = "select * from has where id_has = ?" ;

		this.preparedStatement = connection.prepareStatement(sql) ;
		
		this.preparedStatement.setInt(1, id);
		
	this.resultSet = this.preparedStatement.executeQuery();
		
		
	while (this.resultSet.next()) {
		
		cli = new Has(	);
		
		break ;
	}
	
	return cli ;
	}

	public List<Has> getall() throws SQLException {
		
		 List<Has> list = new ArrayList<Has>() ;
		
		String sql = "select * from lign_cmd" ;

		this.statement = connection.createStatement();
		
	this.resultSet = 	this.statement.executeQuery(sql);
		
		
	while (this.resultSet.next()) {
		
		list.add(new Has(	));
	}
		
		return list;
	}

	

}
