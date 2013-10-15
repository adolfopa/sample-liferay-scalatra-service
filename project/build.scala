import sbt._
import Keys._
import org.scalatra.sbt._
import org.scalatra.sbt.PluginKeys._
import com.mojolly.scalate.ScalatePlugin._
import ScalateKeys._

object SampleLiferayScalatraServiceBuild extends Build {
  val Organization = "org.xardon"
  val Name = "Sample Liferay Scalatra Service"
  val Version = "0.1.0-SNAPSHOT"
  val ScalaVersion = "2.10.2"
  val ScalatraVersion = "2.2.1"

  lazy val project = Project (
    "sample-liferay-scalatra-service",
    file("."),
    settings = Defaults.defaultSettings ++ ScalatraPlugin.scalatraWithJRebel ++ scalateSettings ++ Seq(
      organization := Organization,
      name := Name,
      version := Version,
      scalaVersion := ScalaVersion,

      resolvers += Classpaths.typesafeReleases,
      resolvers += "Local Maven Repository" at "file://" + Path.userHome.absolutePath + "/.m2/repository",

      libraryDependencies ++= Seq(
        "org.scalatra" %% "scalatra"           % ScalatraVersion,
        "org.scalatra" %% "scalatra-scalatest" % "2.2.1" % "test",
        
        "ch.qos.logback" % "logback-classic" % "1.0.6" % "runtime",
        
        "joda-time" % "joda-time" % "2.3",
        
        "org.eclipse.jetty"       % "jetty-webapp"  % "8.1.8.v20121106"     % "container",
        "org.eclipse.jetty.orbit" % "javax.servlet" % "3.0.0.v201112011016" % "container;provided;test" artifacts (Artifact("javax.servlet", "jar", "jar")),
		
        "org.scalatra" %% "scalatra-json"  % "2.2.1",
		"org.json4s"   %% "json4s-jackson" % "3.2.4",
		"org.json4s"   %% "json4s-ext"     % "3.2.4",
		
		"javax.portlet"      % "portlet-api"    % "2.0"    % "provided",
		"com.liferay.portal" % "portal-service" % "6.1.20" % "provided"
      )
    )
  )
}
