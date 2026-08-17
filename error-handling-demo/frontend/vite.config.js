import { defineConfig } from 'vite'

export default defineConfig({
  server: {
    port: 5173,
    proxy: {
      '/rest': 'http://localhost:8080'
    }
  }
})
