const path = require('path');
const { CleanWebpackPlugin } = require("clean-webpack-plugin");
const TSConfigPathsPlugin = require('tsconfig-paths-webpack-plugin');

module.exports = {
  mode: "development",
  // Enable sourcemaps for debugging webpack's output.
  devtool: "source-map",
  resolve: {
    // Add '.ts' and '.tsx' as resolvable extensions.
    extensions: [".wasm", ".ts", ".tsx", ".mjs", ".cjs", ".js", ".json"],
   plugins: [new TSConfigPathsPlugin()],
  },
  entry: {
    app: "./src/index.tsx"
  },
  output: {
    filename: "rule-audit-app.js",
    // We drop the final bundle into play's assets folder.
    path: path.resolve(__dirname, '../../public/javascript'),
  },
  module: {
    rules: [
      {
        test: /\.ts(x?)$/,
        exclude: /node_modules/,
        use: [
          {
            loader: "ts-loader"
          }
        ]
      },
      // All output '.js' files will have any sourcemaps re-processed by 'source-map-loader'.
      {
        enforce: "pre",
        test: /\.js$/,
        loader: "source-map-loader"
      }
    ]
  },
  plugins: [new CleanWebpackPlugin()]
};