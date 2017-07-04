FROM gradle

USER root

COPY . /project
WORKDIR /project


CMD gradle test