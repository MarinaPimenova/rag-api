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

## Dockerized Approach -> to run LLM
Run Ollama + Mistral inside Docker to expose a local OpenAI-compatible API at http://localhost:11434.

Way#1 - run docker-compose with model pulling
```shell
cd /mnt/c/Users/Marina_Pimenova/sb-projects/rag-api/docker-config
# stop running containers
docker-compose down --remove-orphans --volumes

docker-compose -f ./docker-llama3-llm.yml --env-file ./config/.env.dev up
```

Way#2 - ollama & Web UI
```shell
cd /mnt/c/Users/Marina_Pimenova/sb-projects/rag-api/docker-config
# stop running containers
docker-compose down --remove-orphans --volumes

#This will start Ollama and Open WebUI containers in detached mode (-d).
docker-compose -f ./docker-ollama.yml up -d

docker exec -it <container_id> ollama <command>
# Pull model
docker exec -it bf1fa026d23b ollama pull llama3.2

```

WEB UI is available on the http://127.0.0.1:8080

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


