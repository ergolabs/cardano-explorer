package org.ergoLabs.cardanoExplorer.http

import sttp.model.StatusCode
import sttp.tapir.json.circe.jsonBody
import sttp.tapir.{Endpoint, EndpointInput, endpoint, oneOf, oneOfDefaultMapping, oneOfMapping}

object defs {

  def baseEndpointDef(basePrefix: EndpointInput[Unit]): Endpoint[Unit, ApiErr, Unit, Any] =
    endpoint
      .in(basePrefix)
      .errorOut(
        oneOf(
          oneOfMapping(
            StatusCode.NotFound,
            jsonBody[ApiErr.NotFound].description("Not found")
          ),
          oneOfMapping(
            StatusCode.BadRequest,
            jsonBody[ApiErr.BadRequest].description("Bad request")
          ),
          oneOfDefaultMapping(jsonBody[ApiErr.UnknownErr].description("Unknown error"))
        )
      )
      .asInstanceOf[Endpoint[Unit, ApiErr, Unit, Any]]
}
