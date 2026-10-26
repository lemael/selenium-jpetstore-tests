FROM maven:3.9-eclipse-temurin-21

# Notwendige Abhängigkeiten installieren
USER root

RUN apt-get update && apt-get install -y \
    wget \
    gnupg \
    ca-certificates \
    fonts-liberation \
    && wget -q -O - https://dl.google.com/linux/linux_signing_key.pub \
       | gpg --dearmor -o /usr/share/keyrings/google-chrome.gpg \
    && echo "deb [arch=amd64 signed-by=/usr/share/keyrings/google-chrome.gpg] https://dl.google.com/linux/chrome/deb/ stable main" \
       > /etc/apt/sources.list.d/google-chrome.list \
    && apt-get update \
    && apt-get install -y google-chrome-stable \
    && rm -rf /var/lib/apt/lists/*

# Arbeitsverzeichnis festlegen
WORKDIR /app

# Maven-Datei kopieren
COPY pom.xml .

# Abhängigkeiten herunterladen
RUN mvn dependency:go-offline

# Quellcode kopieren
COPY src ./src

# Tests ausführen
CMD ["mvn", "test"]