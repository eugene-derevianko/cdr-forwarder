package com.symulakr.telegram.bot;

import com.symulakr.telegram.bot.model.Cdr;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CdrRepository extends JpaRepository<Cdr, Integer> {

   Cdr findFirstByOrderByIdDesc();

}
