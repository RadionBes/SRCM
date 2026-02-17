import { defineConfig } from 'vite'
import react from '@vitejs/plugin-react'

// https://vite.dev/config/
export default defineConfig({
  plugins: [react()],
  css: {
    modules: {
      // Настройки CSS модулей
      localsConvention: 'camelCase',
      scopeBehaviour: 'local',
      generateScopedName: '[name]__[local]___[hash:base64:5]'
    },
    preprocessorOptions: {
      scss: {
        // Дополнительные опции для SCSS
        // additionalData: `@import "/src/styles/variables.scss";`, // если есть глобальные переменные
        silenceDeprecations: ['legacy-js-api'],
        quietDeps: true
      }
    }
  },
  resolve: {
    alias: {
      // Если используете алиасы
      '@': '/src',
    }
  }
})
