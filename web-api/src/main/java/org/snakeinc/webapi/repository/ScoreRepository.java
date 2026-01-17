package org.snakeinc.webapi.repository;

import org.snakeinc.webapi.entity.Score;
import org.springframework.data.repository.CrudRepository;

interface ScoreRepository extends CrudRepository<Score, Integer> {
}
