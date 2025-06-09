# Generative AI and Large Language Models

# Simple Retrieval-Augmented Generation (RAG) Web App

This project demonstrates a minimal **RAG system** using:

- **Backend (Spring Boot 3 + Java 17)**:
    - Retriever Service: retrieves relevant knowledge from PostgreSQL+pgvector
    - Generator Service:  generate answers
    - Session-based ChatHistory
    - Token management for OpenAI API calls
    - Memory window (only last N messages kept)
    - Swagger UI for easy API testing

- **Frontend (ReactJS + Vite)**:
    - Single-page chat app
    - Maintains sessionId (UUID) locally
    - Displays conversation between user and AI

---

## 🛠 Technologies Used
- **Backend**: Java 17, Spring Boot 3, PostgreSQL with pgvector
- **Frontend**: ReactJS (Vite)
- **Vector Search**: pgvector extension in PostgreSQL
- **OpenAI**: For text generation via Spring Boot AI

---

## How to Run


## Docker
```shell
cd /mnt/c/Users/Marina_Pimenova/sb-projects/rag-api/docker-config
``` 
```shell
docker-compose --env-file ./config/.env.dev up
```

## Manually configure the PgVectorStore
See more [PGvector](https://docs.spring.io/spring-ai/reference/api/vectordbs/pgvector.html)
```text
Apply sql/create_vector_store_table.sql to postgre DB
```

### Backend
```bash
cd rag-api
./mvnw spring-boot:run
```
### Frontend
````shell
cd rag-spa
npm install
npm run dev

Frontend URL: http://localhost:5173
````


