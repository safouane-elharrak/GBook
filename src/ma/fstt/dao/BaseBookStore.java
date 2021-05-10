package ma.fstt.dao;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import ma.fstt.model.BookStore;

public class BaseBookStore extends BaseDao<BookStore> {

	public BaseBookStore() throws SQLException {
		super();
		// TODO Auto-generated constructor stub
	}

	@Override
	public void save(BookStore object) throws SQLException{
		
		String sql = "insert into bookstore (name , adresse , email,telephone) values (?, ? ,? ,?)" ;
		
		this.preparedStatement = connection.prepareStatement(sql);
		
		// mapping objet relation 
		
		this.preparedStatement.setString(1,object.getName() );
		this.preparedStatement.setString(2,object.getAddresse() );
		this.preparedStatement.setString(3,object.getEmail() );
		this.preparedStatement.setString(4,object.getTelephone() );
		
		this.preparedStatement.execute();				
	}

	@Override
	public void update(BookStore object) throws SQLException{
String sql = "UPDATE `BookStore` SET `name`=?,`adresse`=?,`email`=?,`telephone`=? WHERE id_bookstore = ?";
		
		this.preparedStatement = this.connection.prepareStatement(sql);
				
		this.preparedStatement.setString(1, object.getName());
		this.preparedStatement.setString(2, object.getAddresse());
		this.preparedStatement.setString(3, object.getEmail());
		this.preparedStatement.setString(4, object.getTelephone());
		this.preparedStatement.setInt(5, object.getId_bookstore());
		this.preparedStatement.execute();
		
	}

	@Override
	public void delete(BookStore object) throws SQLException{
String sql = "delete `bookstore` from `bookstore` WHERE id_bookstore = ?";
		
		this.preparedStatement = this.connection.prepareStatement(sql);
	
		this.preparedStatement.setInt(1, object.getId_bookstore());
		this.preparedStatement.execute();
		
	}

	@Override
	public List<BookStore> getAll() throws SQLException{
		
		
		List<BookStore> lsit  = new ArrayList() ;
		
		String sql = "select * from bookstore" ;
		
		this.statement = connection.createStatement();
		
	  this.resultSet = 	this.statement.executeQuery(sql) ;
	  
	  // iteration 
	  
	  while( this.resultSet.next()) {
		  
		  lsit.add(new	BookStore(this.resultSet.getInt(1) ,this.resultSet.getString(2) ,
				  this.resultSet.getString(3)  , this.resultSet.getString(4)));	  
	  }
		
		return lsit ;		
	}

	@Override
	public BookStore getOne(int id ) throws SQLException {
		// TODO Auto-generated method stub
		BookStore  book   = new BookStore() ;
		
		String sql = "select * from bookstore where id_bookstore = ?" ;
		
		this.preparedStatement = connection.prepareStatement(sql);
		this.preparedStatement.setInt(1, id);
		
	  this.resultSet = 	this.preparedStatement.executeQuery() ;
	  
	  // iteration 
	  
	  while( this.resultSet.next()) {
		  
		  book = new	BookStore(this.resultSet.getInt(1) ,this.resultSet.getString(2) ,
				  this.resultSet.getString(3)  , this.resultSet.getString(4));
		  
		  break ;		  
	  }		
		return book ;
	}
	
}
