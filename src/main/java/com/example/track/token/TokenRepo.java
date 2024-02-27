//package com.example.track.token;
//
//import java.util.List;
//import java.util.Optional;
//
//import org.springframework.data.jpa.repository.Query;
//
//public interface TokenRepo {
//	
//	 @Query(value = """
//		      select t from Token t inner join UserEntity u\s
//		      on t.user.id = u.id\s
//		      where u.id = :id and (t.expired = false or t.revoked = false)\s
//		      """)
//	
//	List<Token> findAllValidTokenByUser(Integer id);
//
//	Optional<Token> findByToken(String token);
//
//}
