FROM golang:1.19-alpine as builder

RUN mkdir -p /home/service
WORKDIR /home/service

COPY ./main.go .

RUN go build ./main.go

FROM alpine
COPY --from=builder /home/service/main .
RUN chmod +x ./main

CMD [ "./main" ]
