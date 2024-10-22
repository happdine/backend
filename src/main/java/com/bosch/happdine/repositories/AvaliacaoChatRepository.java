package com.bosch.happdine.repositories;

import com.bosch.happdine.models.AvaliacaoChatModel;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface AvaliacaoChatRepository extends JpaRepository<AvaliacaoChatModel, UUID> {

}
