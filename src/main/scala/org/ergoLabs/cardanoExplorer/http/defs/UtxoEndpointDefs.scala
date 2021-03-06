package org.ergoLabs.cardanoExplorer.http.defs

import org.ergoLabs.cardanoExplorer.domain.{FullTxOut, Id}
import org.ergoLabs.cardanoExplorer.http.ApiErr
import org.ergoLabs.cardanoExplorer.http.definitions._
import sttp.tapir._
import sttp.tapir.json.circe._

object UtxoEndpointDefs {

  private val prefix = "utxo"

  def getUtxoById: Endpoint[Id, ApiErr, FullTxOut, Any] =
    baseEndpointDef
      .in(prefix / path[Id])
      .out(jsonBody[FullTxOut])

  def getUtxos: Endpoint[Unit, ApiErr, List[FullTxOut], Any] =
    baseEndpointDef
      .in(prefix)
      .out(jsonBody[List[FullTxOut]])

  def getHeight: Endpoint[Unit, ApiErr, Int, Any] =
    baseEndpointDef
      .in("block" / "height")
      .out(jsonBody[Int])

}
