FROM golang:1.11.12-alpine3.9 AS build

#################################
#        Telegraf version       #
#################################
ENV TELEGRAF_VERSION=1.12.0

#################################
#         Telegraf build        #
#################################
RUN apk add --no-cache --virtual build-deps gcc git make musl-dev dep
RUN go get github.com/influxdata/telegraf

WORKDIR $GOPATH/src/github.com/influxdata/telegraf
RUN git checkout -b $TELEGRAF_VERSION $TELEGRAF_VERSION
RUN make

FROM golang:1.11.12-alpine3.9

#################################z
#         Program version       #
#################################
ENV CONFD_VERSION=0.16.0

#################################
#    Copy telegraf binary       #
#################################
COPY --from=build $HOME/go/src/github.com/influxdata/telegraf/telegraf /bin/telegraf

#################################
#    Make telegraf directory    #
#################################
WORKDIR /telegraf

RUN mkdir -p conf && \
    mkdir -p data && \
    mkdir -p logs

#################################
#         Utility tools         #
#################################
WORKDIR /bin

RUN apk --no-cache add bash
RUN apk add --update curl
RUN wget https://github.com/kelseyhightower/confd/releases/download/v${CONFD_VERSION}/confd-${CONFD_VERSION}-linux-amd64 -O confd
RUN chmod +x confd

ADD confd /etc/confd
ADD scripts/ /bin/
RUN chmod +x /bin/entrypoint.sh

ENTRYPOINT ["/bin/entrypoint.sh"]
