import sbt._
import sbt.Keys._
import org.portablescala.sbtplatformdeps.PlatformDepsPlugin.autoImport._
import org.scalajs.jsdependencies.sbtplugin.JSDependenciesPlugin
import org.scalajs.jsdependencies.sbtplugin.JSDependenciesPlugin.autoImport._

object Dependencies {

  object Ver {

    // Exported
    val scala2          = "2.13.14"
    val scala3          = "3.4.2"
    val scalaJsDom      = "2.8.0"
    val scalaJsReact    = "2.1.2"
    val scalatags       = "0.13.1"
    val univEq          = "2.0.1"

    // Internal
    val cats            = "2.12.0"
    val microlibs       = "4.2.1"
    val nyaya           = "1.1.0"
    val reactJs         = "17.0.1"
    val utest           = "0.8.3"
  }

  object Dep {
    val cats                 = Def.setting("org.typelevel"                     %%% "cats-core"               % Ver.cats)
    val microlibsCompileTime = Def.setting("com.github.japgolly.microlibs"     %%% "compile-time"            % Ver.microlibs)
    val microlibsTestUtil    = Def.setting("com.github.japgolly.microlibs"     %%% "test-util"               % Ver.microlibs)
    val nyayaGen             = Def.setting("com.github.japgolly.nyaya"         %%% "nyaya-gen"               % Ver.nyaya)
    val nyayaProp            = Def.setting("com.github.japgolly.nyaya"         %%% "nyaya-prop"              % Ver.nyaya)
    val nyayaTest            = Def.setting("com.github.japgolly.nyaya"         %%% "nyaya-test"              % Ver.nyaya)
    val scalaCompiler        = Def.setting("org.scala-lang"                      % "scala-compiler"          % scalaVersion.value)
    val scalaJsDom           = Def.setting("org.scala-js"                      %%% "scalajs-dom"             % Ver.scalaJsDom)
    val scalaJsReactCore     = Def.setting("com.github.japgolly.scalajs-react" %%% "core"                    % Ver.scalaJsReact)
    val scalaJsReactCoreGen  = Def.setting("com.github.japgolly.scalajs-react" %%% "core-generic"            % Ver.scalaJsReact)
    val scalaJsReactDummy    = Def.setting("com.github.japgolly.scalajs-react" %%% "util-dummy-defaults"     % Ver.scalaJsReact)
    val scalaJsReactTest     = Def.setting("com.github.japgolly.scalajs-react" %%% "test"                    % Ver.scalaJsReact)
    val scalaReflect         = Def.setting("org.scala-lang"                      % "scala-reflect"           % scalaVersion.value)
    val scalatags            = Def.setting("com.lihaoyi"                       %%% "scalatags"               % Ver.scalatags)
    val univEq               = Def.setting("com.github.japgolly.univeq"        %%% "univeq"                  % Ver.univEq)
    val utest                = Def.setting("com.lihaoyi"                       %%% "utest"                   % Ver.utest)
  }

  def addReactJsDependencies(scope: Configuration): Project => Project =
    _.enablePlugins(JSDependenciesPlugin)
      .settings(
        jsDependencies ++= Seq(

          "org.webjars.npm" % "react" % Ver.reactJs % scope
            /        "umd/react.development.js"
            minified "umd/react.production.min.js"
            commonJSName "React",

          "org.webjars.npm" % "react-dom" % Ver.reactJs % scope
            /         "umd/react-dom.development.js"
            minified  "umd/react-dom.production.min.js"
            dependsOn "umd/react.development.js"
            commonJSName "ReactDOM",

          "org.webjars.npm" % "react-dom" % Ver.reactJs % scope
            /         "umd/react-dom-server.browser.development.js"
            minified  "umd/react-dom-server.browser.production.min.js"
            dependsOn "umd/react-dom.development.js"
            commonJSName "ReactDOMServer",
        ),
      )
}
