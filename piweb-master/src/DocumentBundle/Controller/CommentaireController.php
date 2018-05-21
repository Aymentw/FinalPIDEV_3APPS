<?php

namespace DocumentBundle\Controller;

use Symfony\Bundle\FrameworkBundle\Controller\Controller;
use Symfony\Component\HttpFoundation\Request;
use UtilisateurBundle\Entity\Commentaire;
use UtilisateurBundle\Entity\Document;
use DocumentBundle\Form\DocumentType;
use DocumentBundle\Form\CommentaireType;

class CommentaireController extends Controller
{
    public function ajouterAction(Request $request)
    {
        $em = $this->getDoctrine()->getManager();
        $id = $request->get("idd");
        $doc = $em->getRepository("UtilisateurBundle:Document")->find($id);
        if ($request->isMethod("POST")) {

            $commentaire = new Commentaire();
            $commentaire->setIdDocument($doc);
            $commentaire->setIdUser($this->getUser());
            $commentaire->setContenu($request->get("idcomm"));
            $commentaire->setPost(new \DateTime('now'));
            $em->persist($commentaire);
            $em->flush();
        }

        return $this->redirectToRoute("detail_document",array("id"=>$id));
    }



}