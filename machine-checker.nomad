job "example" {
  datacenters = ["dc1"]
  group "machine_checker"{
    task "application"{
      driver="java"
      config {
        jar_path = "target/machineCheckerService-0.0.1-SNAPSHOT.jar"
      }
      env {
        connect_user_token="BMhzWBqavy7bQMAafZUxUtSURDhcYBGV4Q"
      }

    }
  }
}