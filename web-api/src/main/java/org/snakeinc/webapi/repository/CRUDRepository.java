package org.snakeinc.webapi.repository;

import org.snakeinc.webapi.entity.Player;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
interface CRUDRepository extends CrudRepository<Player, Integer> {
}
