package main

import (
	"fmt"
	"log"
	"net/http"
	"os"
)

func main() {
	hostname, err := os.Hostname()
	if err != nil {
		log.Panic("Error getting hostname")
	}

	mux := http.NewServeMux()

	mux.HandleFunc("/users/1", func(w http.ResponseWriter, r *http.Request) {
		fmt.Println("Request")
		w.Header().Set("Content-Type", "application/json")
		w.WriteHeader(http.StatusOK)
		jsonData := []byte(`{"id":1, "firstName":"Leander", "lastName":"Steiner", "email":"leander.steiner@hof-university.de", "hostname":"` + hostname + `"}`)
		w.Write(jsonData)
	})

	server := http.Server{
		Addr:    ":8080",
		Handler: mux,
	}

	err = server.ListenAndServe()
	if err != nil {
		if err != http.ErrServerClosed {
			panic(err)
		}
	}
}
