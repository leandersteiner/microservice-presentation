package main

import (
	"encoding/json"
	"fmt"
	"net/http"
)

func main() {
	mux := http.NewServeMux()

	mux.HandleFunc("/", func(w http.ResponseWriter, r *http.Request) {
		fmt.Println("Request")
		resp, err := http.Get("http://localhost:8080/users/1")
		if err != nil {
			panic(err)
		}
		defer resp.Body.Close()

		var data struct {
			Id        int    `json:"id"`
			FirstName string `json:"firstName"`
			LastName  string `json:"lastName"`
			Email     string `json:"email"`
		}

		err = json.NewDecoder(resp.Body).Decode(&data)
		if err != nil {
			panic(err)
		}

		w.Header().Set("Content-Type", "application/json")
		w.WriteHeader(http.StatusOK)
		jd, err := json.Marshal(data)
		if err != nil {
			panic(err)
		}
		jsonData := []byte(jd)
		w.Write([]byte(jsonData))
	})

	server := http.Server{
		Addr:    "localhost:8081",
		Handler: mux,
	}

	err := server.ListenAndServe()
	if err != nil {
		if err != http.ErrServerClosed {
			panic(err)
		}
	}
}
