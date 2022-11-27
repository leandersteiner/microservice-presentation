package main

import (
	"fmt"
	"log"
	"net"

	"github.com/leandersteiner/microservice-presentation/examples/synchronous-communication/grpc/producer/user"
	"golang.org/x/net/context"
	"google.golang.org/grpc"
)

type App struct {
	port int
	user.UnimplementedUserServiceServer
}

func NewApp(port int) *App {
	return &App{
		port: port,
	}
}

func (a App) GetUser(ctx context.Context, request *user.Empty) (*user.User, error) {
	fmt.Println("Request")
	response := &user.User{
		Id:        1,
		FirstName: "Leander",
		LastName:  "Steiner",
		Email:     "leander.steiner@hof-university.de",
	}

	return response, nil
}

func (a App) Run() {
	con, err := net.Listen("tcp", fmt.Sprintf(":%d", a.port))
	if err != nil {
		log.Fatalf("failed to listen on port %d, error: %v", a.port, err)
	}

	grpcServer := grpc.NewServer()
	user.RegisterUserServiceServer(grpcServer, a)

	if err := grpcServer.Serve(con); err != nil {
		log.Fatalf("failed to serve grpc on port %d", a.port)
	}
}

func main() {
	app := NewApp(4000)
	app.Run()
}
