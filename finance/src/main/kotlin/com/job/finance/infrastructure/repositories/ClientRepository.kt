package com.job.finance.infrastructure.repositories

import com.job.finance.infrastructure.entities.ClientEntity
import org.springframework.data.repository.kotlin.CoroutineCrudRepository
import org.springframework.stereotype.Repository

@Repository
interface ClientRepository : CoroutineCrudRepository<ClientEntity, Long> {}