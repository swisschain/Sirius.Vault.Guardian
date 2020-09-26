FROM scrooge/gradle:jdk11-s390x as build
ADD . /home/gradle/project
WORKDIR /home/gradle/project
RUN gradle clean build printProperties


FROM s390x/adoptopenjdk:11.0.8_10-jre-hotspot
RUN apt-get update && \
    apt-get install unzip && \
    apt-get clean
COPY --from=build /home/gradle/project/build/distributions/Sirius.Vault.Guardian-0.1.zip .
RUN unzip -q Sirius.Vault.Guardian-0.1.zip && \
    rm Sirius.Vault.Guardian-0.1.zip && \
    mv Sirius.Vault.Guardian-* Sirius.Vault.Guardian && \
    chmod +x Sirius.Vault.Guardian/bin/Sirius.Vault.Guardian && \
    sed -i -e 's/\r$//' Sirius.Vault.Guardian/bin/Sirius.Vault.Guardian

ENTRYPOINT ["/Sirius.Vault.Guardian/bin/Sirius.Vault.Guardian"]
CMD ["--console"]

EXPOSE 5000
