# Estágio 1: Build da aplicação com Maven
# Usamos uma imagem base que contém o JDK 17, conforme especificado no seu pom.xml
FROM eclipse-temurin:17-jdk-jammy as builder

# Define o diretório de trabalho dentro do contêiner
WORKDIR /app

# Copia o wrapper do Maven
COPY .mvn/ .mvn
COPY mvnw pom.xml ./

# --- LINHA ADICIONADA ---
# Dá permissão de execução ao script do Maven Wrapper
RUN chmod +x ./mvnw

# Baixa as dependências do projeto. O Docker guardará essa camada em cache.
RUN ./mvnw dependency:go-offline

# Copia o resto do código-fonte da aplicação
COPY src ./src

# Compila a aplicação e gera o arquivo .jar, pulando os testes
RUN ./mvnw clean install -DskipTests

# Estágio 2: Criação da imagem final, otimizada e menor
# Usamos uma imagem base apenas com o ambiente de execução Java (JRE)
FROM eclipse-temurin:17-jre-jammy

WORKDIR /app

# Copia o arquivo .jar gerado no estágio de build para a imagem final
COPY --from=builder /app/target/lia-0.0.1-SNAPSHOT.jar ./app.jar

# Expõe a porta 8080, que é a porta que sua aplicação usa
EXPOSE 8080

# Comando para iniciar a aplicação quando o contêiner for executado
ENTRYPOINT ["java", "-jar", "app.jar"]