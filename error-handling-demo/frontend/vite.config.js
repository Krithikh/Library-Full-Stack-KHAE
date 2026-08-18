import { defineConfig } from 'vite'

export default defineConfig({
  server: {
    port: 5173,
    strictPort: true,
    proxy: {
      '/rest': 'http://localhost:8082'
    }
  }
})
