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
WORKDIR /spring

RUN mkdir -p conf && \
    mkdir -p data && \
    mkdir -p logs


ENTRYPOINT ["/bin/entrypoint.sh"]
