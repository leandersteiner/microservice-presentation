package main

import (
	"fmt"
	"io"
	"log"
	"net/http"
	"sync"
)

func main() {
	routines := 20

	var wg sync.WaitGroup
	wg.Add(routines)

	for i := 0; i <= routines; i++ {
		go request("http://localhost:8080", &wg)
	}

	wg.Wait()
}

func request(url string, wg *sync.WaitGroup) {
	resp, err := http.Get(url)
	if err != nil {
		log.Panic("Error requesting")
	}
	defer resp.Body.Close()
	b, err := io.ReadAll(resp.Body)
	fmt.Println(string(b))
	wg.Done()
}
