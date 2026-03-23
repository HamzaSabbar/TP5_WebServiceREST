package org.example.resource;

import org.example.model.Sale;
import org.example.service.SaleService;

import javax.ws.rs.*;
import javax.ws.rs.core.*;
import java.util.List;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;

@Path("/sales")
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
@Tag(name = "Sales", description = "API de gestion des ventes ElectroShop")
public class SaleResource {

    private SaleService saleService = SaleService.getInstance();

    @GET
    @Operation(
        summary = "Lister toutes les ventes",
        description = "Retourne la liste complète des ventes",
        responses = {
            @ApiResponse(responseCode = "200", description = "Liste des ventes récupérée avec succès")
        }
    )
    public Response getAllSales() {
        List<Sale> sales = saleService.getAllSales();
        return Response.ok(sales).build();
    }

    @GET
    @Path("/{id}")
    @Operation(
        summary = "Récupérer une vente par ID",
        description = "Retourne une vente à partir de son identifiant",
        responses = {
            @ApiResponse(responseCode = "200", description = "Vente trouvée"),
            @ApiResponse(responseCode = "404", description = "Vente non trouvée")
        }
    )
    public Response getSaleById(
            @Parameter(description = "Identifiant de la vente", required = true)
            @PathParam("id") Long id) {

        Sale sale = saleService.getSaleById(id);

        if (sale == null) {
            return Response.status(Response.Status.NOT_FOUND)
                    .entity("{\"error\": \"Vente non trouvée\"}")
                    .build();
        }

        return Response.ok(sale).build();
    }

    @POST
    @Operation(
        summary = "Ajouter une vente",
        description = "Crée une nouvelle vente et calcule automatiquement le total",
        responses = {
            @ApiResponse(responseCode = "201", description = "Vente créée avec succès"),
            @ApiResponse(responseCode = "400", description = "Données invalides")
        }
    )
    public Response createSale(Sale sale) {
        if (sale.getProduct() == null || sale.getProduct().isEmpty()) {
            return Response.status(Response.Status.BAD_REQUEST)
                    .entity("{\"error\": \"Le nom du produit est obligatoire\"}")
                    .build();
        }

        Sale created = saleService.addSale(sale);
        return Response.status(Response.Status.CREATED)
                .entity(created)
                .build();
    }

    @PUT
    @Path("/{id}")
    @Operation(
        summary = "Mettre à jour une vente",
        description = "Modifie une vente existante à partir de son identifiant",
        responses = {
            @ApiResponse(responseCode = "200", description = "Vente mise à jour avec succès"),
            @ApiResponse(responseCode = "404", description = "Vente non trouvée")
        }
    )
    public Response updateSale(
            @Parameter(description = "Identifiant de la vente à modifier", required = true)
            @PathParam("id") Long id,
            Sale sale) {

        Sale updated = saleService.updateSale(id, sale);

        if (updated == null) {
            return Response.status(Response.Status.NOT_FOUND)
                    .entity("{\"error\": \"Vente non trouvée\"}")
                    .build();
        }

        return Response.ok(updated).build();
    }

    @DELETE
    @Path("/{id}")
    @Operation(
        summary = "Supprimer une vente",
        description = "Supprime une vente à partir de son identifiant",
        responses = {
            @ApiResponse(responseCode = "200", description = "Vente supprimée avec succès"),
            @ApiResponse(responseCode = "404", description = "Vente non trouvée")
        }
    )
    public Response deleteSale(
            @Parameter(description = "Identifiant de la vente à supprimer", required = true)
            @PathParam("id") Long id) {

        boolean deleted = saleService.deleteSale(id);

        if (!deleted) {
            return Response.status(Response.Status.NOT_FOUND)
                    .entity("{\"error\": \"Vente non trouvée\"}")
                    .build();
        }

        return Response.ok("{\"message\": \"Vente supprimée avec succès\"}")
                .build();
    }

    @GET
    @Path("/count")
    @Operation(
        summary = "Compter les ventes",
        description = "Retourne le nombre total des ventes",
        responses = {
            @ApiResponse(responseCode = "200", description = "Nombre total des ventes retourné avec succès")
        }
    )
    public Response countSales() {
        int count = saleService.countSales();
        return Response.ok("{\"count\": " + count + "}").build();
    }
}