package org.example;

import org.glassfish.grizzly.http.server.HttpServer;
import org.glassfish.jersey.grizzly2.httpserver.GrizzlyHttpServerFactory;
import org.glassfish.jersey.server.ResourceConfig;
import org.glassfish.jersey.server.ServerProperties;
import org.glassfish.jersey.server.wadl.WadlFeature;

import java.net.URI;

public class Application {

    // URL de base du serveur REST
    public static final String BASE_URI = "http://localhost:9090/api/";

    /**
     * Démarre le serveur HTTP Grizzly
     */
    public static HttpServer startServer() {
        // Configuration de Jersey
    	final ResourceConfig config = new ResourceConfig()
    	        .packages("org.example.resource", "org.example.config")
    	        .register(io.swagger.v3.jaxrs2.integration.resources.OpenApiResource.class)
    	        .property(ServerProperties.WADL_FEATURE_DISABLE, false);
        ;  // Scanner le package des ressources

        // Créer et démarrer le serveur
        return GrizzlyHttpServerFactory.createHttpServer(URI.create(BASE_URI), config);
    }

    public static void main(String[] args) {
        try {
            final HttpServer server = startServer();

            System.out.println("=".repeat(60));
            System.out.println(" Serveur REST démarré avec succès!");
            System.out.println(" URL de base: " + BASE_URI);
            System.out.println("=".repeat(60));
            System.out.println("\n Endpoints disponibles:");
            System.out.println("  GET    " + BASE_URI + "products          - Liste tous les produits");
            System.out.println("  GET    " + BASE_URI + "products/{id}     - Détails d'un produit");
            System.out.println("  POST   " + BASE_URI + "products          - Créer un produit");
            System.out.println("  PUT    " + BASE_URI + "products/{id}     - Modifier un produit");
            System.out.println("  DELETE " + BASE_URI + "products/{id}     - Supprimer un produit");
            System.out.println("  GET    " + BASE_URI + "products/search?q=... - Rechercher");
            System.out.println("  GET    " + BASE_URI + "products/count    - Compter les produits");
            System.out.println("\n" + "=".repeat(60));
            System.out.println("Appuyez sur Entrée pour arrêter le serveur...");
            System.out.println("=".repeat(60));

            System.in.read();

            server.shutdownNow();
            System.out.println("\n Serveur arrêté.");

        } catch (Exception e) {
            System.err.println(" Erreur lors du démarrage du serveur: " + e.getMessage());
            e.printStackTrace();
        }
    }
}
//http://localhost:9090/api/application.wadl
     /* http://localhost:8080/api/products
        http://localhost:8080/api/products/1
        http://localhost:8080/api/products/count
        http://localhost:8080/api/products/search?q=samsung */