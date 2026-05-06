FROM ubuntu:latest
LABEL authors="ichotu"

ENTRYPOINT ["top", "-b"]