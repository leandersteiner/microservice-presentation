package main

import (
	"fmt"
	"log"

	"github.com/nats-io/nats.go"
)

func main() {
	nc, err := nats.Connect(nats.DefaultURL)
	if err != nil {
		log.Fatal(err)
	}
	defer nc.Close()

	nc.Subscribe("user.created", func(msg *nats.Msg) {
		fmt.Printf("Sending confirmation email to: %s\n", msg.Data)
	})

	wait := make(chan any)
	<-wait
}
