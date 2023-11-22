package com.challenger.repositories.choice;

import com.challenger.model.Session.SessionVotes;
import com.challenger.model.choice.Choice;
import org.springframework.data.jpa.repository.JpaRepository;

public interface IChoiceRepository extends JpaRepository<Choice, String> {
    Boolean existsBySessionVotesAndCpfUser(SessionVotes sessionVotes, String cpfUser);
}
