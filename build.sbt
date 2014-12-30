import sbt._
import Keys._
//import sbtrelease._
//import ReleaseStateTransformations._
//import ReleasePlugin._
//import ReleaseKeys._

name := "specs2-matchers-workshop"

organization := "com.wix"

scalaVersion := "2.11.4"

scalacOptions ++= Seq("-unchecked", "-deprecation", "-feature")

scalacOptions in Test ++= Seq("-Yrangepos")

resolvers += "Sonatype OSS Snapshots" at "https://oss.sonatype.org/content/repositories/snapshots"

libraryDependencies ++= Seq(
  "org.specs2" %% "specs2-core" % "2.4.15" % "test"/*,
  "joda-time" %% "joda-time" % "2.6" % "test"*/
)

//publishTo := {
//  val nexus = "https://oss.sonatype.org/"
//  if (isSnapshot.value)
//    Some("snapshots" at nexus + "content/repositories/snapshots")
//  else
//    Some("releases" at nexus + "service/local/staging/deploy/maven2")
//}

licenses := Seq("Apache 2.0" -> url("http://www.opensource.org/licenses/Apache-2.0"))

homepage := Some(url("https://github.com/noam-almog/specs2-matchers-workshop.git"))

//publishMavenStyle := true
//
//publishArtifact in Test := false
//
//pomIncludeRepository := { _ => false }

//pomExtra := (
//  <scm>
//    <url>https://github.com/noam-almog/specs2-matchers-workshop.git</url>
//    <connection>scm:git:git@github.com:wix/future-perfect.git</connection>
//  </scm>
//  <developers>
//    <developer>
//      <id>noam-almog</id>
//      <name>Shai Yallin</name>
//      <url>http://www.shaiyallin.com</url>
//    </developer>
//  </developers>
//)
//
//lazy val publishSignedAction = { st: State =>
//  val extracted = Project.extract(st)
//  val ref = extracted.get(thisProjectRef)
//  extracted.runAggregated(com.typesafe.sbt.pgp.PgpKeys.publishSigned in Global in ref, st)
//}
//
//releaseSettings
//
//releaseProcess := Seq[ReleaseStep](
//    checkSnapshotDependencies       //
//  , runTest                         //
//  , inquireVersions                 //
//  , setReleaseVersion               //
//  , commitReleaseVersion            //performs the initial git checks
//  , tagRelease                      //
//  , publishArtifacts.copy(action =  //uses publish-signed instead of publish if configured.
//      publishSignedAction
//    )
//  , setNextVersion                  //
//  , commitNextVersion               //
//  , pushChanges                     //also checks that an upstream branch is properly configured
//)
//
