package io.chrisdavenport.log4cats.iolog4s

import cats.effect.Sync
import io.chrisdavenport.log4cats.Logger
import org.iolog4s.{Logger => Base}

object IoLog4sLogger {

  def fromName[F[_]: Sync](name: String) = 
    fromLogger[F](Base.create[F](name))

  def fromClass[F[_]: Sync](clazz: Class[_]) =
    fromLogger[F](Base.create[F](clazz))

  def getLogger[F[_]: Sync] = fromLogger[F](Base.create[F])

  def fromLogger[F[_]: Sync](logger: Base[F]): Logger[F] = new Logger[F]{
    def debug(t: Throwable)(message: => String): F[Unit] = logger.debug(t)(message)
    def debug(message: => String): F[Unit] = logger.debug(message)
    def error(t: Throwable)(message: => String): F[Unit] = logger.error(t)(message)
    def error(message: => String): F[Unit] = logger.error(message)
    def info(t: Throwable)(message: => String): F[Unit] = logger.info(t)(message)
    def info(message: => String): F[Unit] = logger.info(message)
    def isDebugEnabled: F[Boolean] = Sync[F].delay(logger.isDebugEnabled)
    def isErrorEnabled: F[Boolean] = Sync[F].delay(logger.isErrorEnabled)
    def isInfoEnabled: F[Boolean] = Sync[F].delay(logger.isInfoEnabled)
    def isTraceEnabled: F[Boolean] = Sync[F].delay(logger.isTraceEnabled)
    def isWarnEnabled: F[Boolean] = Sync[F].delay(logger.isWarnEnabled)
    def trace(t: Throwable)(message: => String): F[Unit] = logger.trace(t)(message)
    def trace(message: => String): F[Unit] = logger.trace(message)
    def warn(t: Throwable)(message: => String): F[Unit] = logger.warn(t)(message)
    def warn(message: => String): F[Unit] = logger.warn(message)
  }

}