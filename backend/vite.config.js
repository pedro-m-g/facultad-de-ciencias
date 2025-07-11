import { defineConfig, loadEnv } from 'vite';
import laravel from 'laravel-vite-plugin';
import react from '@vitejs/plugin-react';

export default defineConfig(({ mode }) => {
  // Load `.env`, `.env.[mode]`, etc. into process.env
  const env = loadEnv(mode, process.cwd(), '');

  // Prefer .env values, fall back to any already-exported shell vars, then default
  const port = Number(env.VITE_PORT ?? process.env.VITE_PORT) || 5173;

  return {
    plugins: [
      laravel({
        input: 'resources/js/app.jsx',
        refresh: true,
      }),
      react(),
    ],
    server: {
      port
    },
  };
});
