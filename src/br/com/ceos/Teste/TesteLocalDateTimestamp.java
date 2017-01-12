package br.com.ceos.Teste;

import java.sql.Timestamp;
import java.time.LocalDateTime;
import java.util.Date;

public class TesteLocalDateTimestamp {
	
	public static void main(String args[]){
		
		Timestamp time = new Timestamp(new Date().getTime());
		
		System.out.println(time.toLocalDateTime()); 
		
	}

}
