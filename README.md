# Generative AI and Large Language Models

# Simple Retrieval-Augmented Generation (RAG) Web App

This project demonstrates a minimal **RAG system** using:

- **Backend (Spring Boot 3 + Java 17)**:
    - Retriever Service: retrieves relevant knowledge from PostgreSQL+pgvector
    - Generator Service: uses Semantic Kernel to generate answers
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
- **Backend**: Java 17, Spring Boot 3, Semantic Kernel Java SDK, PostgreSQL with pgvector
- **Frontend**: ReactJS (Vite)
- **Vector Search**: pgvector extension in PostgreSQL
- **OpenAI**: For text generation via Semantic Kernel integration

---

## How to Run

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

