<?php
/**
 * Created by PhpStorm.
 * User: Manai
 * Date: 25/04/2018
 * Time: 00:10
 */

namespace ApiBundle\Controller;

use Symfony\Bundle\FrameworkBundle\Controller\Controller;
use Symfony\Component\HttpFoundation\JsonResponse;
use Symfony\Component\HttpFoundation\Request;
use Symfony\Component\Serializer\Encoder\JsonEncode;
use Symfony\Component\Serializer\Normalizer\ObjectNormalizer;
use Symfony\Component\Serializer\Serializer;
use UtilisateurBundle\Entity\Document;
use UtilisateurBundle\Entity\User;

class MobileController extends Controller
{
    public function ajouterAction(Request $request)
    {
        $em = $this->getDoctrine()->getManager();
        $doc = new Document();
        $doc->setEtat("en attente");
        $doc->setTitre($request->get('titre'));
        $doc->setDescription($request->get('description'));
        $doc->setNiveau($request->get('niveau'));
        $doc->setMatiere($request->get('matiere'));
        $doc->setPath($request->get('path'));
        $us = $em->getRepository(User::class)->find($request->get('idu'));
        $doc->setIdUser($us);
        $em->persist($doc);
        $em->flush();
        $serializer = new Serializer([new ObjectNormalizer()]);
        $formatted = $serializer->normalize($doc);
        return new JsonResponse($formatted);
    }

    public function listAction()
    {
        $docs = $this->getDoctrine()->getRepository("UtilisateurBundle:Document")->findAll();
        $encoder = new JsonEncode();
        $normalizer = new ObjectNormalizer();
        $normalizer->setCircularReferenceHandler(function($object){
            return $object->getId();
        });
        $serializer = new Serializer([$normalizer], [$encoder]);
        $formatted = $serializer->normalize($docs);
        return new JsonResponse($formatted);
    }

    public function findAction($id)
    {
        $doc = $this->getDoctrine()->getManager()->getRepository("UtilisateurBundle:Document")->find($id);
        $serializer = new Serializer([new ObjectNormalizer()]);
        $formatted = $serializer->normalize($doc);
        return new JsonResponse($formatted);

    }

}