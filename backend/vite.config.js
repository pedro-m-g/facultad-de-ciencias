import { defineConfig } from 'vite';
import laravel from 'laravel-vite-plugin';
import react from '@vitejs/plugin-react';
-import { fileURLToPath } from 'node:url'
+import { fileURLToPath } from 'node:url';

export default defineConfig({
    plugins: [
        laravel({
            input: 'resources/js/app.jsx',
            refresh: true,
        }),
        react(),
    ],
    server: {
        port: Number(process.env.VITE_PORT) || 5173,
    },
    resolve: {
// At the top of backend/vite.config.js
import { defineConfig } from 'vite';
import laravel from 'laravel-vite-plugin';
import { fileURLToPath } from 'node:url';
import { dirname, resolve } from 'node:path';

const __dirname = dirname(fileURLToPath(import.meta.url));

export default defineConfig({
  plugins: [
    // …
  ],
  resolve: {
    alias: [
      {
        find: '@',
        replacement: resolve(__dirname, 'resources/js'),
      }
    ]
  }
});
    }
});
