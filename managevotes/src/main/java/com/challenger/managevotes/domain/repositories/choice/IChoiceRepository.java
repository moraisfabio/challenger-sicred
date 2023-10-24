package com.challenger.managevotes.domain.repositories.choice;

import com.challenger.managevotes.domain.entities.Session.SessionVotes;
import com.challenger.managevotes.domain.entities.choice.Choice;
import org.springframework.data.jpa.repository.JpaRepository;

public interface IChoiceRepository extends JpaRepository<Choice, String> {
    Boolean existsBySessionVotesAndCpfUser(SessionVotes sessionVotes, String cpfUser);
}
