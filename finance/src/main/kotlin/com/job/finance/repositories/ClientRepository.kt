package com.job.finance.repositories

import com.job.finance.entities.ClientEntity
import org.springframework.data.repository.kotlin.CoroutineCrudRepository
import org.springframework.stereotype.Repository

@Repository
interface ClientRepository : CoroutineCrudRepository<ClientEntity, Long> {}