# App de Apoio à Saúde Mental e Psicossocial

Aplicativo mobile desenvolvido com base nas diretrizes da NR1, como proposta para o programa da Softtek. O projeto visa oferecer recursos de suporte à saúde mental no ambiente de trabalho.

---

## 🧠 Funcionalidades

- Avaliação de riscos psicossociais (ex: questionários, autoavaliação)
- Acompanhamento emocional (diário de humor, check-in)
- Canal de escuta e orientações
- Visualização de dados consolidados (gráficos e evolução)
- Lembretes e apoio personalizado

---

## 🧱 Arquitetura

O projeto segue o padrão **MVVM (Model-View-ViewModel)**, garantindo desacoplamento, testabilidade e separação de responsabilidades.

- **View:** Jetpack Compose com Material 3
- **ViewModel:** AndroidX Lifecycle + LiveData
- **Model:** Room (SQLite) + Retrofit (API)

---

## 🔌 Tecnologias e Bibliotecas

- **Jetpack Compose** (UI declarativa)
- **Navigation Compose** (roteamento)
- **Room** (persistência local SQLite)
- **Retrofit** + **Gson Converter** (consumo de APIs REST)
- **MockWebServer** (testes com API simulada)
- **LiveData** + **ViewModel** (ciclo de vida e reatividade)
- **JUnit / Espresso** (testes unitários e instrumentados)

---

## 💡 Observações

- Toda comunicação com APIs é feita de forma assíncrona utilizando Retrofit.
- Dados persistidos localmente via Room, com mapeamento automático usando annotations.
- Interface 100% construída com Compose, sem uso de XML.
- Projeto preparado para expansão e integração em contextos reais de saúde corporativa.

---

## 👨‍💻 Autor

João Paulo
