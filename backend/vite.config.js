import { defineConfig } from 'vite';
import laravel from 'laravel-vite-plugin';
import react from '@vitejs/plugin-react';
import { fileURLToPath } from 'node:url';
import { dirname, resolve } from 'node:path';

const __dirname = dirname(fileURLToPath(import.meta.url));

export default defineConfig({
  plugins: [
    laravel({
      input: 'resources/js/app.jsx',
      refresh: true,
    }),
    react(),
  ],
// backend/vite.config.js

-import { defineConfig } from 'vite';
+import { defineConfig, loadEnv } from 'vite';

-export default defineConfig({
+export default defineConfig(({ mode }) => {
+  // Load `.env`, `.env.[mode]`, etc. into process.env
+  const env = loadEnv(mode, process.cwd(), '');
+
+  // Prefer .env values, fall back to any already-exported shell vars, then default
+  const port = Number(env.VITE_PORT ?? process.env.VITE_PORT) || 5173;
+
+  return {
     plugins: [
       // …
     ],
-  server: {
-    port: Number(process.env.VITE_PORT) || 5173,
-  },
+    server: {
+      port,
+    },
   };
-});
+});
  resolve: {
    alias: {
      '@': resolve(__dirname, 'resources/js'),
    },
  },
});
